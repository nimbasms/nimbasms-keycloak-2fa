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

# Setup

    1 - Duplicate browser authenticator flow
    2 - In current flow add new step
    3 - In list select and add `Nimba SMS Authentication`
    4 - Now you can configure authenticator parameters : `Sender Name`, `Service Id`, `Secret Token`

# Credit
Nimba SMS