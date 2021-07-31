import os
import sys
import datetime
import pymysql

ROOT_DIR = "C:/Users/jenny/projects/"
LOG_DIR = "websvr_attack/"
servers = ["Server1/", "Server2/", "Server3/", "Server4/"]

BASE = ROOT_DIR+LOG_DIR

def convert_timestamp(timestamp):
    # ex : 07/Feb/2017:01:13:08 +0900
    timestamp = timestamp.replace('+', " ")
    ts, gap = timestamp.split()

    gap = int(gap[:2])
    utc = datetime.datetime.strptime(ts, '%d/%b/%Y:%H:%M:%S')
    
    gap = datetime.timedelta(hours=gap)
    result = utc + gap

    return result.strftime('%Y-%m-%d %H:%M:%S')


def ssl_parse(path):
    with open(path) as f:
        for line in f.readlines():
            try:
                obj = {}

                block1, block2, block3 = line.split("\"")

                # block2
                # method + uri + protocol
                if block2 != "-":
                    block2 = block2.split()
                    obj["method"] = block2[0]
                    obj["uri"] = block2[1]
                    obj["protocol"] = block2[2]

                # block1
                # timestamp + ip
                block1 = block1.replace('[', ',').replace(']', ',')
                block1 = block1.split(",")
                obj["timestamp"] = convert_timestamp(block1[1])

                block1 = block1[2].split()
                obj["ip"] = block1[0][2:]

                # block3
                # res_data_size
                block3 = block3.replace('\n', '')
                obj["res_data_size"] = block3

            except Exception as e:
                with open("error.log", 'a') as f:
                    f.write(f"{e}::{line}")



def parse(root, filename):
    path = root+"/"+filename
    if "error" in filename:
        return

    if "ssl_request_log" in filename:
        ssl_parse(path)
        return

    with open(path) as f:
        for line in f.readlines():
            try:
                obj = {}
                block1, block2, *block3 = line.split("\"")

                # block2
                # method + uri + protocol
                if len(block2) != 1:
                    block2 = block2.split()
                    obj["method"] = block2[0]
                    obj["uri"] = block2[1]
                    obj["protocol"] = block2[2]

                # block1
                # ip + userid + timestamp
                block1 = block1.replace('[', ',').replace(']', ',')
                block1 = block1.split(",")
                obj["timestamp"] = convert_timestamp(block1[1])
                print(block1[1], obj["timestamp"])

                block1 = block1[0].split()
                obj["ip"] = block1[0][2:]

                for element in block1[1:]:
                    if element != "-":
                        obj["userid"] = element


                # block3 list
                # 0: res_code + res_data_size
                res_code, res_data_size = block3[0].split()
                obj["res_code"] = res_code
                if res_data_size.isdigit():
                    obj["res_data_size"] = res_data_size

                # 1: referer
                if len(block3) > 1 and block3[1] != "-":
                    referer = block3[1]
                    obj["referer"] = referer


                # 2: user_agent
                if len(block3) > 3 and block3[3] != "-":
                    user_agent = block3[3]
                    obj["user_agent"] = user_agent

            except Exception as e:
                with open("error.log", 'a') as f:
                    f.write(f"{e}::{line}")


def main():
    for (root,dirs,files) in os.walk(BASE):

        for log in files:
            parse(root,log)

if __name__ == "__main__":
    #parse(BASE+"Server3","ssl_request_log")
    sys.exit(main())