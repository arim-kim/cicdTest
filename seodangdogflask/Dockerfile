FROM python:3.11-slim

# 현재 디렉토리의 모든 파일들을 컨테이너의 /app 디렉토리에 복사한다.
COPY . /app

# flask의 작업 위치가 /app이라는 뜻
WORKDIR /app

# pip upgrade 해주기
RUN pip install --upgrade pip
# requirements.txt load 패키지 설치하기
RUN pip install -r requirements.txt

# FLASK env 명시하기 
ENV FLASK_APP=app.py

# flask 는 5000번 
EXPOSE 5000

# 실행하기
CMD ["flask", "run"]

