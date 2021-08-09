from set_kisa import TEST_RESULT_DIR
import datetime
import detector as dt
import re

normal_exp = r"(?P<ip>.*) (?P<remote_log_name>.*?)(%s|%s.*?%s)?(?P<userid>.*?) \[(?P<date>.*?)(?= ) (?P<timezone>.*?)\] \"(?P<request>(?P<method>[A-Z]+) (?P<uri>.*)? (?P<protocol>HTTPS?/[0-9.]+)?|-)\" (?P<status_code>\d{3})? (?P<res_data_size>\d*|-)?( (\"(?P<referer>.*?)\") (\"(?P<user_agent>.*?)\"))?"
ssl_exp = r"\[(?P<date>.*?)(?= ) (?P<timezone>.*?)\] (?P<ip>.*) (.*?) (.*?) \"(?P<request>(?P<method>[A-Z]+) (?P<uri>.*)? (?P<protocol>HTTPS?/[0-9.]+)?|-)\" (?P<res_data_size>\d*|-)?"

np = re.compile(normal_exp)
sp = re.compile(ssl_exp)

# UTC -> Asia/Seoul
def convert_timezone(date, timezone):
    # ex : 07/Feb/2017:01:13:08 +0900

    timezone = int(timezone[:2])
    utc = datetime.datetime.strptime(date, '%d/%b/%Y:%H:%M:%S')
    
    timezone = datetime.timedelta(hours=timezone)
    result = utc + timezone

    return result.strftime('%Y-%m-%d %H:%M:%S')


# filter xss & sql_injection & rfi
def get_malcode(obj):
    if dt.is_xss(obj["method"], obj["uri"]): return 4
    elif dt.is_sql_injection(obj["method"], obj["uri"]): return 1
    elif dt.is_rfi(obj["method"], obj["uri"]): return 2
    elif dt.is_wshell(obj["uri"]): return 3
    return -1


# parse normal format
def parse_normal(root, filename):
    path = root+"/"+filename

    with open(path) as f:
        for line in f.readlines():
            try:
                line.replace('"', '\"')
                result = np.match(line)

                obj = {}
                obj['ip'] = result.group('ip')
                obj['userid'] = result.group('userid')
                obj['timestamp'] = convert_timezone(result.group('date'), result.group('timezone'))

                obj['method'] = result.group('method')
                obj['uri'] = result.group('uri')
                obj['protocol'] = result.group('protocol')
            
                obj['res_data_size'] = result.group('res_data_size')

                if (referer := result.group('referer')):
                    obj['referer'] = referer
                
                if (user_agent := result.group('user_agent')):
                    obj['user_agent'] = user_agent


                obj['mal_code'] = None
                mal_code = get_malcode(obj)

                # abnormal
                if mal_code != -1:
                    obj['mal_code'] = mal_code

                # send obj to db

            except Exception as e:
                # send obj to unknown
                pass


# parse ssl_request_log format
def parse_ssl(root, filename):
    path = root + "/" + filename

    with open(path) as f:

        for line in f.readlines():
            try:
                line.replace('"', '\"')
                result = sp.match(line)

                obj = {}
                obj['ip'] = result.group('ip')
                obj['timestamp'] = convert_timezone(result.group('date'), result.group('timezone'))

                obj['method'] = result.group('method')
                obj['uri'] = result.group('uri')
                obj['protocol'] = result.group('protocol')
                obj['res_data_size'] = result.group('res_data_size')

                obj['mal_code'] = None
                mal_code = get_malcode(obj)

                # abnormal
                if mal_code != -1:
                    obj['mal_code'] = mal_code

                # send obj to db

            except Exception as e:
                # send obj to unknown
                pass