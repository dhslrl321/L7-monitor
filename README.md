#### 해당 프로젝트를 기획하고 협업의 과정 그리고 개발, 배포하며 느꼈던 감정의 회고를 보시려면 아래 블로그를 참고해주시기 바랍니다

#### 블로그 포스팅 1. [팀 빌딩 및 협업에 대한 고민](https://wonit.tistory.com/577) @Tistory

#### 블로그 포스팅 2. [프론트엔드 기술 선정과 고민들](https://wonit.tistory.com/578?category=869199) @Tistory

#### 블로그 포스팅 3. [백엔드를 개발하며 했던 고민들](https://wonit.tistory.com/579?category=869199) @Tistory

#### 블로그 포스팅 4. [인프라 개발과 배포를 하며 만난 고민들](https://wonit.tistory.com/580?category=869199) @Tistory

---

# 🖥 L7 에서 동작하는 Access Log 기반 트래픽 분석 및 보안 위협 분석 서비스

<div align="center"> 
<img style="margin: 15px" src="https://img.shields.io/badge/react.js-17.0.2-9cf.svg" alt="badge-react" />
<img style="margin: 15px" src="https://img.shields.io/badge/Next.js-11.1.2-inactive.svg" alt="badge-react"/>
<img style="margin: 15px" src="https://img.shields.io/badge/Redux-4.1.1-red.svg" alt="badge-react" />
<img style="margin: 15px" src="https://img.shields.io/badge/SpringBoot-2.5.4-green.svg" alt="badge-react" />
<img style="margin: 15px" src="https://img.shields.io/badge/QueryDsl-4.4.0-success.svg" alt="badge-react"/>
<img style="margin: 15px" src="https://img.shields.io/badge/NHNCloud-Instance-blue.svg" alt="badge-react"/>
<img style="margin: 15px" src="https://img.shields.io/badge/AWS-S3-orange.svg" alt="badge-react"/>
<img style="margin: 15px" src="https://img.shields.io/badge/Nginx-2.5.4-green.svg" alt="badge-react" />
</div>

#### 해당 서비스는 BoB 10기 보안제품개발 트랙의 1차 팀 프로젝트로, 사내의 **Web Application** 이 기존에 저장해놓은 **Access Log** 를 기반으로 Application Layer 에서의 트래픽을 분석하고 권고사항을 제안해주는 **통합 Dashboard** 를 제공한다.

# 목차

1. ### [팀원 소개](#-팀원-소개)
2. ### [사용 기술 및 언어](#-사용-기술-및-언어)

- - 2-1 [개발 전략](#-개발-전략)
- - 2-2 [배포 전략](#-배포-전략)

3. ### [서비스 흐름 및 UI](#-서비스-흐름-및-UI)

- - 3-1 [서비스 플로우](#-Service-Flow)
- - 3-2 [User Interface](#-User-Interface)
- - 3-3 [DB Schema](#-DB-Schema)

4. ### [시연 영상 및 사용법](#-시연-영상-및-사용법)

- - 4-1 [시연 영상](#-시연-영상)
- - 4-2 [사용법](#-사용법)

# 👨‍👩‍👧‍👦 팀원 소개

|    👨‍👨‍👧    |                        팀장                        |                             Agent                              |                        Web                        |                             Web                             |
| :------: | :------------------------------------------------: | :------------------------------------------------------------: | :-----------------------------------------------: | :---------------------------------------------------------: |
| **Name** |      👩🏻‍💻 [김혜송](https://github.com/lauvsong)      |            🧑‍💻 [김진욱](https://github.com/NownS)            | 🧑‍💻 [전승현](https://github.com/SeunghyeonJeon) |        🧑‍💻 [장원익](https://github.com/dhslrl321)         |
| **Role** | 일정 관리 및 팀원 조율 & **로그 분석 Daemon** 개발 | Client Side **로그 전달 Agent** 개발 및 탐지 룰 및 정확도 개선 |           **React UI** 개발 및 API 연동           | **Backend API 개발** 및 Front 통신 모듈 개발 및 인프라 구성 |

# 🎸 사용 기술 및 언어

![skill](https://github.com/dhslrl321/L7-monitor/blob/master/assets/skill.png)

# 🪜 개발 및 배포 전략

### 개발 전략

- **Front End**
  - Container Presenter Pattern
    - [container](https://github.com/dhslrl321/L7-monitor/tree/master/frontend/src/components/container) 코드
    - [presenter](https://github.com/dhslrl321/L7-monitor/tree/master/frontend/src/components/presenter) 코드
  - Build Time 의 동적 env 주입을 위한 shell-script
    - [create-env-file.sh](https://github.com/dhslrl321/L7-monitor/blob/master/frontend/create-env-file.sh)
- **Back End**
  - [Layered Architecture](https://github.com/dhslrl321/L7-monitor/tree/master/backend/src/main/java/com/example/l7monitor)
  - TDD 기반 Test Coverage 90% 이상 안정적인 API
    - [Test Codes](https://github.com/dhslrl321/L7-monitor/tree/master/backend/src/test/java/com/example/l7monitor)
    - [BDD by using BDDMockito](https://github.com/dhslrl321/L7-monitor/blob/master/backend/src/test/java/com/example/l7monitor/service/TrafficServiceTest.java)
- **Agent & Daemon**
  - Bottom Up For Modularization

### 배포 전략

- **Container Runtime : Docker**
  - [Front End Dockerfile](https://github.com/dhslrl321/L7-monitor/blob/master/frontend/Dockerfile)
  - [Back End Dockerfile](https://github.com/dhslrl321/L7-monitor/blob/master/backend/Dockerfile)
  - [Reverse Proxy Dockerfile](https://github.com/dhslrl321/L7-monitor/blob/master/reverse-proxy/Dockerfile)
- **Multiple Container Management Tool : docker-compose**
  - 동적 IP Env 주입을 위한 Docker Build
  - service [docker-compose.yml](https://github.com/dhslrl321/L7-monitor/blob/master/docker-compose.yml)

# 📑 서비스 흐름 및 UI

# Service Flow

![service-flow](https://github.com/dhslrl321/L7-monitor/blob/master/assets/service-flow.png)

# User Interface

![ui](https://github.com/dhslrl321/L7-monitor/blob/master/assets/ui.png)

- **실시간 트래픽 모니터링**
  - Line Graph
  - 5분, 하루, 일주일 트래픽 시각화
- **보안 위협 트래픽 분류 및 금일 보안 Level 제공**
  - 정상 대비 악성 트래픽 비율 및 보안 수준
- **로그 history 확인**
  - Table Format
  - 하루에 발생한 Full Log Format 시각화
  - 식별 불가 로그의 시각화
- **웹 공격에 따른 분류 및 시각화**
  - Pie Chart
  - SQL-Injection, RFI, XSS, Web-Shell 공격 분류 시각화

# DB Schema

![db](https://github.com/dhslrl321/L7-monitor/blob/master/assets/db.png)

- **Tables**
  - total
  - abnormal
  - unknown_log
  - mal_code

# 🎥 시연 영상 및 사용법

# 시연 영상

[![시연](https://img.youtube.com/vi/v_qsQnZcDeI/0.jpg)](https://www.youtube.com/watch?v=v_qsQnZcDeI)

# 사용법

```
$ git clone https://github.com/BORI-BoB/L7-monitor.git

$ git checkout -t origin/develop

$ ./execute-service.sh
```
