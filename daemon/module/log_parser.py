import datetime
import re
from . import detector as dt
from .DB import db_controller as db

normal_exp = r"(?P<ip>\S+) (?P<remote_log_name>\S+) (?P<userid>\S+) (- )?\[(?P<date>.+)(?= ) (?P<timezone>\S+)\] \"(?P<request>(?P<method>[A-Z]+) (?P<uri>\S+) (?P<protocol>HTTPS?/[0-9.]+)?|-)?\" (?P<status_code>\d{3}) (?P<res_data_size>\d+|-)?( (\"(?P<referer>\S+)\") (\"(?P<user_agent>\S+)\"))?"
ssl_exp = r"\[(?P<date>\S+)(?= ) (?P<timezone>\S+)\] (?P<ip>\S+) (.*?) (.*?) \"(?P<request>(?P<method>[A-Z]+) (?P<uri>\S+) (?P<protocol>HTTPS?/[0-9.]+)?|-)\" (?P<res_data_size>\d{3}|-)?"
np = re.compile(normal_exp)
sp = re.compile(ssl_exp)

# initialize obj attribute sequence
def init_obj():
    obj = {}
    obj['mal_code'] = None
    obj['ip'] = None
    obj['userid'] = None
    obj['timestamp'] = None
    obj['method'] = None
    obj['uri'] = None
    obj['protocol'] = None
    obj['res_code'] = None
    obj['res_data_size'] = None
    obj['referer'] = None
    obj['user_agent'] = None

    return obj

# initialize unknown log's obj
def init_unknown():
    obj = {}
    obj['ip'] = None
    obj['data'] = None

    return obj

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
    return None


# parse normal format
def parse_normal(conn, cursor, path):
    obj = {}

    with open(path) as f:
        for line in f.readlines():
            try:
                result = np.match(line)

                obj = init_obj()

                obj['ip'] = result.group('ip')
                obj['userid'] = result.group('userid')
                obj['timestamp'] = convert_timezone(result.group('date'), result.group('timezone'))

                obj['method'] = result.group('method')
                obj['uri'] = result.group('uri')
                obj['protocol'] = result.group('protocol')
            
                obj['res_data_size'] = result.group('res_data_size')
                
                obj['referer'] = result.group('referer')
                obj['user_agent'] = result.group('user_agent')

                obj['mal_code'] = get_malcode(obj)

            except Exception as e:
                obj = init_unknown()
                obj['ip'] = line[:9]
                obj['data'] = line[9:].replace('"', "\'")

            finally:
                table_name = db.get_table(obj)
                db.insert(conn, cursor, table_name, obj)
                conn.commit()


# parse ssl_request_log format
def parse_ssl(conn, cursor, path):

    with open(path) as f:

        for line in f.readlines():
            try:
                result = sp.match(line)

                obj = init_obj()

                obj['ip'] = result.group('ip')
                obj['timestamp'] = convert_timezone(result.group('date'), result.group('timezone'))

                obj['method'] = result.group('method')
                obj['uri'] = result.group('uri')
                obj['protocol'] = result.group('protocol')
                obj['res_data_size'] = result.group('res_data_size')

                obj['mal_code'] = get_malcode(obj)

            except Exception as e:
                obj = init_unknown()
                obj['ip'] = line[:9]
                obj['data'] = line[9:].replace('"', "\'")

            finally:
                table_name = db.get_table(obj)
                db.insert(conn, cursor, table_name, obj)
                conn.commit()
