# input format: GET /uri... 
def is_sql_injection(method, uri):
    if method != "GET": return False

    rules = ["\'", "%27", "%20", "UNION", "+"]
    for rule in rules:
        if rule in uri:
            return True
    
    return False

# input format: GET /uri... 
def is_rfi(method, uri):
    if method != "GET ": return False

    rules = [".", "%00", "/", "http"]
    for rule in rules:
        if rule in uri: return True

    return False

# input format: previous log byte count, current log byte count
def is_wshell(curr_byte, prev_byte):
    return abs(curr_byte - prev_byte) > 10000