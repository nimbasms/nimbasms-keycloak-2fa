# Keycloak 2FA Nimba SMS Authenticator

Keycloak Authentication Provider implementation to get a 2nd-factor authentication with a OTP/code/token send via Nimba SMS.

# Build and Installation

```sh
mvn compile & mvn package
```

# Deployement - Your can download last release

```sh
cp -r ./target/*.jar /opt/keycloak/providers/
```

# Credit
Nimba SMS