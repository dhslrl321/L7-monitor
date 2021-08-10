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

def resolve_table_name(data):
    if data.get('data'): return "unknown"
    if data['mal_code']: return "abnormal"
    return "total"
    

def insert(cursor, table, data):
    try:
        fs = ','.join(list(map(lambda x: '`' + x + '`', [*data.keys()])))
        vs = ','.join(list(map(lambda x: '%(' + x + ')s', [*data.keys()])))
        sql = "INSERT INTO `%s` (%s) VALUES (%s)" % (table, fs, vs)
        cursor.execute(sql, data)
    except:
        raise Exception("Error : Fail to insert record")