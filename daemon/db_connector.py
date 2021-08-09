import pymysql
import json

def connect():
    with open('config.json', 'r') as f:
        config = json.load(f)

    db = pymysql.connect(
        user = config['user'],
        passwd = config['passwd'],
        host = config['host'],
        db = config['db'],
        charset = config['charset']
    )

    return db