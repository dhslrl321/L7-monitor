import os
import sys
import log_parser as lp
import test_parser as tp
from db_controller import *

## *ROOT_DIR는 개인별 websvr_attack 폴더 저장되어있는 디렉토리로 설정 요망
ROOT_DIR = "C:/Users/jenny/projects/"
LOG_DIR = "websvr_attack/"
BASE = ROOT_DIR+LOG_DIR

TEST_RESULT_DIR = "test/"

connection = None

try:
    db = connect()
    cursor = db.cursor()
except:
    if db:
        db.rollback()
        raise Exception("Error: Fail to connect db")
finally:
    if db:
        db.close()


def work(root, filename):
    if "ssl_request_log" in filename:
        data = lp.parse_ssl(root, filename)
    else:
        data = lp.parse_normal(root, filename)

    table_name = resolve_table_name(data)
    insert(cursor, table_name, data)
    db.commit()


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