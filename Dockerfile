# ビルド用のJDKを使用（Gradleインストールしてビルドを実行）
FROM openjdk:17-jdk-slim as build

# ビルド用のGradleをインストール
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.0-bin.zip && \
    unzip gradle-8.0-bin.zip && \
    mv gradle-8.0 /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/local/bin/gradle


# 作業ディレクトリを指定
WORKDIR /app

# Gradleの依存関係とソースコードをコンテナにコピー
COPY build.gradle settings.gradle ./
COPY src ./src

# Gradleでビルドを実行
RUN gradle build -x test


# 実行用のJDKを使用
FROM openjdk:17-jdk-slim

# 作業ディレクトリを指定
WORKDIR /app

# ビルド済みJARファイルをコピー
COPY --from=build /app/build/libs/*.jar app.jar

# アプリケーション起動
ENTRYPOINT ["java", "-jar", "app.jar"]
