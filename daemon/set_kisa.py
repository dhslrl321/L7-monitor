import os
import sys
import log_parser as lp
import test_parser as tp
from db_connector import connect

## *ROOT_DIR는 개인별 websvr_attack 폴더 저장되어있는 디렉토리로 설정 요망
ROOT_DIR = "C:/Users/jenny/projects/"
LOG_DIR = "websvr_attack/"
BASE = ROOT_DIR+LOG_DIR

TEST_RESULT_DIR = "test/"

connection = None

try:
    connection = connect()
    cursor = connection.cursor()
except:
    if connection:
        connection.rollback()
        raise Exception("Error: Fail to connect db")
finally:
    if connection:
        connection.close()

def work(root, filename):
    if "ssl_request_log" in filename:
        lp.parse_ssl(root, filename)
    else:
        lp.parse_normal(root, filename)

def main():
    try:
        if not os.path.exists(TEST_RESULT_DIR):
            os.makedirs(TEST_RESULT_DIR)
    except:
        raise Exception(f"Error: Creating directory \'{TEST_RESULT_DIR}\'")

    for (root,_,files) in os.walk(BASE):

        for log in files:
            if "error" in log: continue
            work(root,log)

if __name__ == "__main__":
    sys.exit(main())
    #lp.parse_normal(BASE+"Server3","ssl_request_log") ## test