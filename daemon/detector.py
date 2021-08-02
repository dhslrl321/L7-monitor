# 공격여부 탐지 모듈

# input format: GET /uri... 
def is_sql_injection(method, uri):
    if method != "GET": return False

    rules = ["\'", "%27", "%20", "UNION", "+"]

    uri = uri.split("?")
    if len(uri) == 1: return False
    param = uri[1]

    for rule in rules:
        if rule in param:
            return True
    
    return False

# input format: GET /uri... 
def is_rfi(method, uri):
    if method != "GET ": return False

    rules = [".", "%00", "/", "http"]

    uri = uri.split("?")
    if len(uri) == 1: return False
    param = uri[1]

    for rule in rules:
        if rule in param: return True

    return False

# input format: previous log byte count, current log byte count
def is_wshell(curr_byte, prev_byte):
    return abs(curr_byte - prev_byte) > 100000