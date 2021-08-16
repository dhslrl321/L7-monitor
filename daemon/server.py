import os
import sys
import socket
import module.log_parser as lp
import module.DB.db_controller as db

def DBconnect():
    conn = None
    
    try:
        conn = db.connect()
        cursor = conn.cursor()
    except:
        if conn:
            conn.rollback()
        conn.close()
        raise Exception("Error: Fail to connect db")
    
    return conn, cursor  
    
if __name__ == "__main__":
    conn, cursor = DBconnect()
    
    sPort = 7777
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serverSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    serverSocket.bind(("", sPort))
    serverSocket.listen(1)
    while True:
        (connectionSocket, clientAddress) = serverSocket.accept()
        print('Connection requested from', clientAddress)
        while True:
            message = connectionSocket.recv(2048)
            msg = message.decode()
            f = open("tmp.txt", "w")
            f.write(msg)
            f.close()
            print(msg)
            lp.parse_normal(conn, cursor, "tmp.txt")
            
    
