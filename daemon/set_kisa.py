import os
import sys
import log_parser as lp
import test_parser as tp

## *ROOT_DIR는 개인별 websvr_attack 폴더 저장되어있는 디렉토리로 설정 요망
ROOT_DIR = "C:/Users/jenny/projects/"
LOG_DIR = "websvr_attack/"
BASE = ROOT_DIR+LOG_DIR

def work(root, filename):
    if "ssl_request_log" in filename:
        tp.parse_ssl(root, filename)
    else:
        tp.parse_normal(root, filename)

def main():
    for (root,_,files) in os.walk(BASE):

        for log in files:
            if "error" in log: continue
            work(root,log)

if __name__ == "__main__":
    sys.exit(main())
    #lp.parse_normal(BASE+"Server3","ssl_request_log") ## test