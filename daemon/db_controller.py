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

def get_table(data):
    if data.get('data'): return "unknown_log"
    if data['mal_code']: return "abnormal"
    return "total"
    
def set_attributes(conn, data):
    keys = []
    values = []
    for k,v in data.items():
        if v and v != "-": 
            keys.append('`'+k+'`')
            v = conn.escape_string(str(v))
            values.append(v if v.isdecimal() else '\"'+ v +'\"')    

    return ','.join(keys), ','.join(values)

def insert(conn, cursor, table, data):
    try:
        fs,vs = set_attributes(conn, data)
        sql = "INSERT INTO `%s` (%s) VALUES (%s)" % (table, fs, vs)
        cursor.execute(sql)
    except Exception as e:
        print(sql, data)
        raise Exception(f"Insert Error : {e}")