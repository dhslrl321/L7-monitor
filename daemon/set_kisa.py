import os
import sys
import datetime
import detector as dt

## *ROOT_DIR는 개인별 websvr_attack 폴더 저장되어있는 디렉토리로 설정 요망
ROOT_DIR = "C:/Users/*/projects/"
LOG_DIR = "websvr_attack/"
BASE = ROOT_DIR+LOG_DIR

# UTC -> Asia/Seoul
def convert_timezone(timestamp):
    # ex : 07/Feb/2017:01:13:08 +0900
    timestamp = timestamp.replace('+', " ")
    ts, gap = timestamp.split()

    gap = int(gap[:2])
    utc = datetime.datetime.strptime(ts, '%d/%b/%Y:%H:%M:%S')
    
    gap = datetime.timedelta(hours=gap)
    result = utc + gap

    return result.strftime('%Y-%m-%d %H:%M:%S')


# parse ssl_request_log format
def parse_ssl(root, filename):
    path = root + "/" + filename
    prev_size = 0

    with open(path) as f:

        for line in f.readlines():
            try:
                obj = {}

                """
                block1 : ip + timestamp
                block2 : method + uri + protocol
                block3 :  res_data_size
                """

                # block1, block2, block3 = line.split("\"")         # block2에 \" 포함되어서 생기는 파싱 오류로 인해 변경
                idx_1 = line.find("\"")
                idx_2 = idx_1
                while True:
                    idx_2 = line[idx_2+1:].find("\"") + idx_2 + 1
                    if line[idx_2-1] != "\\":
                        break
                block1 = line[:idx_1]
                block2 = line[idx_1+1:idx_2]
                block3 = line[idx_2+1:]


                # block2
                if block2 != "-":
                    # block2 = block2.split()                       # uri 내부에 space 있는 경우 파싱 오류로 인해 변경
                    # obj["method"] = block2[0]
                    # obj["uri"] = block2[1]
                    
                    idx = block2.index(" ")
                    obj["method"] = block2[:idx]
                    obj["uri"] = block2[idx+1:]

                    # filter xss & sql_injection & rfi
                    if dt.is_xss(obj["method"], obj["uri"]):
                        with open("xss.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue
                    elif dt.is_sql_injection(obj["method"], obj["uri"]):
                        with open("sql_injection.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue
                    elif dt.is_rfi(obj["method"], obj["uri"]):
                        with open("rfi.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue

                    obj["protocol"] = block2[2]
                
                # block3
                # filter webshell
                res_data_size = block3.replace("\n", "").replace(" ", "")
                if not res_data_size.isdecimal():
                    prev_size = 0
                else:
                    res_data_size = int(res_data_size)
                    obj["res_data_size"] = res_data_size

                    if dt.is_wshell(res_data_size, prev_size):
                        prev_size = res_data_size
                        with open("wshell.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue
                    prev_size = res_data_size

                # block1
                block1 = block1.replace('[', ',').replace(']', ',')
                block1 = block1.split(",")
                obj["timestamp"] = convert_timezone(block1[1])

                block1 = block1[2].split()
                obj["ip"] = block1[0][2:]

            except Exception as e:
                with open("unknown.log", 'a') as f:
                    f.write(f"{e}::{line}")


# parse normal format
def parse(root, filename):
    path = root+"/"+filename
    prev_size = 0

    # is error logs
    if "error" in filename:
        return

    if "ssl_request_log" in filename:
        parse_ssl(root, filename)
        return

    with open(path) as f:

        for line in f.readlines():
            try:
                obj = {}

                """
                block1 : ip + userid + timestamp
                block2 : method + uri + protocol
                block3 : 
                    0 - res_code + res_data_size
                    1 - referer
                    3 - user-agent
                """
                
                # block1, block2, *block3 = line.split("\"")        # block2에 \" 포함되어서 생기는 파싱 오류로 인해 변경
                idx_1 = line.find("\"")
                idx_2 = idx_1
                while True:
                    idx_2 = line[idx_2+1:].find("\"") + idx_2 + 1
                    if line[idx_2-1] != "\\":
                        break
                block1 = line[:idx_1]
                block2 = line[idx_1+1:idx_2]
                block3 = line[idx_2+1:].split("\"")

                # block2
                if block2 != "-":
                    # block2 = block2.split()                       # uri 내부에 space 있는 경우 파싱 오류로 인해 변경
                    # obj["method"] = block2[0]
                    # obj["uri"] = block2[1]
                    
                    idx = block2.index(" ")
                    obj["method"] = block2[:idx]
                    obj["uri"] = block2[idx+1:]

                    # filter xss & sql_injection & rfi
                    if dt.is_xss(obj["method"], obj["uri"]):
                        with open("xss.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue
                    elif dt.is_sql_injection(obj["method"], obj["uri"]):
                        with open("sql_injection.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue
                    elif dt.is_rfi(obj["method"], obj["uri"]):
                        with open("rfi.log", 'a') as f:
                            f.write(f"{filename}::{line}")
                        continue

                    obj["protocol"] = block2[2]

                # block3 : 0
                res_code, res_data_size = block3[0].split()
                if res_code.isdecimal():
                    obj["res_code"] = res_code

                # filter webshell
                if not res_data_size.isdecimal():
                    prev_size = 0
                else:
                    res_data_size = int(res_data_size)
                    obj["res_data_size"] = res_data_size

                    if dt.is_wshell(res_data_size, prev_size):
                        prev_size = res_data_size
                        with open("wshell.log", 'a') as f:
                            f.write(f"{filename}::{line}")

                    prev_size = res_data_size
                    continue
                
                # block1
                block1 = block1.replace('[', ',').replace(']', ',')
                block1 = block1.split(",")
                obj["timestamp"] = convert_timezone(block1[1])

                block1 = block1[0].split()
                obj["ip"] = block1[0][2:]

                for element in block1[1:]:
                    if element != "-":
                        obj["userid"] = element

                # block3 : 1
                if len(block3) > 1 and block3[1] != "-":
                    referer = block3[1]
                    obj["referer"] = referer

                # block3 : 2
                if len(block3) > 3 and block3[3] != "-":
                    user_agent = block3[3]
                    obj["user_agent"] = user_agent

            except Exception as e:
                with open("unknown.log", 'a') as f:
                    f.write(f"{e}::{line}")


def main():
    for (root,dirs,files) in os.walk(BASE):

        for log in files:
            parse(root,log)

if __name__ == "__main__":
    sys.exit(main())
    #parse(BASE+"Server3","ssl_request_log")