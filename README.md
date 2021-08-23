> BoB 10기 보안제품개발 트랙 1차 팀 프로젝트

# 🖥 서비스 기획 배경 및 기능 목록

> SW 인재 대란 속에서 잘 하는 개발자, 관리자를 뽑은 것이 힘들어진 요즘 기업의 입장에서는 새로운 인력을 채용하는 것이 부담으로 다가갈 수 있다.

#### 해당 서비스는 사내의 **Web Application** 이 기존에 저장해놓은 **Access Log** 를 기반으로 Application Layer 에서의 트래픽을 분석하고 권고사항을 제안해주는 **통합 Dashboard** 를 제공한다.

# 목차

1. ### [팀원 소개](#-팀원-소개)
2. ### [사용 기술 및 언어](#-사용-기술-및-언어)

- - 2-1 [개발 전략](#-개발-전략)
- - 2-2 [배포 전략](#-배포-전략)

3. ### 서비스 흐름 및 UI

- - 3-1 [서비스 플로우](#-Service-Flow)
- - 3-2 [User Interface](#-User-Interface)
- - 3-3 [DB Schema](#-DB-Schema)

4. ### 시연 영상 및 사용법

- - 4-1 [시연 영상](#-시연-영상)
- - 4-2 [사용법](#-사용법)

- 목차
  - 팀원 소개
  - 사용 기술 및 언어
    - 개발 전략
    - 배포 전략
  - 서비스 흐름 및 구성도
    - Service FLow Architecture
    - DB Shema
  - 시연 및 사용 방법
    - git pull
    - vi docker-compose.yml
    - docker-compose up -d --build

### 기능 목록

- 실시간 트래픽 모니터링
- 보안 위협 트래픽 분류 및 금일 보안 Level 제공
- 로그 history 확인
- 웹 공격에 따른 분류 및 시각화

# 👨‍👩‍👧‍👦 팀원 소개

|    👨‍👨‍👧    |                        팀장                        |                             Agent                              |                        Web                        |                             Web                             |
| :------: | :------------------------------------------------: | :------------------------------------------------------------: | :-----------------------------------------------: | :---------------------------------------------------------: |
| **Name** |      👩🏻‍💻 [김혜송](https://github.com/lauvsong)      |            🧑‍💻 [김진욱](https://github.com/NownS)            | 🧑‍💻 [전승현](https://github.com/SeunghyeonJeon) |        🧑‍💻 [장원익](https://github.com/dhslrl321)         |
| **Role** | 일정 관리 및 팀원 조율 & **로그 분석 Daemon** 개발 | Client Side **로그 전달 Agent** 개발 및 탐지 룰 및 정확도 개선 |           **React UI** 개발 및 API 연동           | **Backend API 개발** 및 Front 통신 모듈 개발 및 인프라 구성 |

# 🎸 사용 기술 및 언어

# 사용 기술 및 언어

![skill](https://github.com/dhslrl321/L7-monitor/blob/master/assets/skill.png)

# 🪜 개발 및 배포 전략

# 개발 전략

- Front End
  - Container Presenter Pattern
    - [container](https://github.com/dhslrl321/L7-monitor/tree/master/frontend/src/components/container) 코드
    - [presenter](https://github.com/dhslrl321/L7-monitor/tree/master/frontend/src/components/presenter) 코드
- Back End
  - [Layered Architecture](https://github.com/dhslrl321/L7-monitor/tree/master/backend/src/main/java/com/example/l7monitor)
  - TDD 기반 Test Coverage 90% 이상 안정적인 API
    - [Test Codes](https://github.com/dhslrl321/L7-monitor/tree/master/backend/src/test/java/com/example/l7monitor)
    - [BDD by using BDDMockito](https://github.com/dhslrl321/L7-monitor/blob/master/backend/src/test/java/com/example/l7monitor/service/TrafficServiceTest.java)

# 배포 전략

- Container Runtime : Docker
  - [Front End Dockerfile](https://github.com/dhslrl321/L7-monitor/blob/master/frontend/Dockerfile)
  - [Back End Dockerfile](https://github.com/dhslrl321/L7-monitor/blob/master/backend/Dockerfile)
  - [Reverse Proxy Dockerfile](https://github.com/dhslrl321/L7-monitor/blob/master/reverse-proxy/Dockerfile)
- Multiple Container Management Tool : docker-compose
  - 동적 IP Env 주입을 위한 Docker Build
  - service [docker-compose.yml](https://github.com/dhslrl321/L7-monitor/blob/master/docker-compose.yml)

# 📑 서비스 흐름 및 UI

# Service Flow

![service-flow](https://github.com/dhslrl321/L7-monitor/blob/master/assets/service-flow.png)

# User Interface

![ui](https://github.com/dhslrl321/L7-monitor/blob/master/assets/ui.png)

# DB Schema

![db](https://github.com/dhslrl321/L7-monitor/blob/master/assets/db.png)

# 🎥 시연 영상 및 사용법

# 시연 영상

[![시연](https://img.youtube.com/vi/v_qsQnZcDeI/0.jpg)](https://www.youtube.com/watch?v=v_qsQnZcDeI)

# 사용법

```
$ git clone https://github.com/BORI-BoB/L7-monitor.git

$ git checkout -t origin/develop

$ ./execute-service.sh
```
