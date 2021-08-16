import socket
import json
import time

class Agent(object):
    def __init__(self, fileName, svName, svPort):
        self.file = open(fileName, 'r')
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.connect((svName, svPort))

    def run(self, second=5):
        while True:
            curr = self.file.tell()
            line = self.file.readline()
            if not line:
                self.file.seek(curr)
                time.sleep(second)
            else:
                self.sendline(line.strip("\n"))

    def sendline(self, line):
        print(line)
        self.socket.send(line.encode())


if __name__ == "__main__":
   
    with open("config.json", "r") as f:
        config = json.load(f)

    SERVER = config["remote"]
    SPORT = int(config["dport"])
    FILENAME = config["file"]

    agent = Agent(FILENAME, SERVER, SPORT)
    agent.run()
