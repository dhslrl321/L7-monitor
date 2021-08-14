import os
import sys
import log_parser as lp
import test_parser as tp
import db_controller as db

## *ROOT_DIR는 개인별 websvr_attack 폴더 저장되어있는 디렉토리로 설정 요망
ROOT_DIR = "C:/Users/jenny/projects/"
LOG_DIR = "websvr_attack/"
BASE = ROOT_DIR+LOG_DIR

TEST_RESULT_DIR = "test/"

def work(conn, cursor, root, filename):
    path = root + "/" + filename
    if "ssl_request_log" in filename:
        lp.parse_ssl(conn, cursor, path)
    else:
        lp.parse_normal(conn, cursor, path)

def main():
    try:
        conn = db.connect()
        cursor = conn.cursor()
    except:
        if conn:
            conn.rollback()
        raise Exception("Error: Fail to connect db")
    
    for (root,_,files) in os.walk(BASE):
        for log in files:
            if "error" in log: continue
            work(conn, cursor, root, log)
    
    conn.close()
    
if __name__ == "__main__":
    sys.exit(main())
    #lp.parse_normal(BASE+"Server3","ssl_request_log") ## test