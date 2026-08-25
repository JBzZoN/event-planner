# Event Planner

A full-stack event planning and vendor management application built with Angular, Spring Boot, PostgreSQL, and Keycloak.

## Architecture

The application follows a separated frontend and backend architecture:

- Angular client for the user interface
- Spring Boot backend acting as a resource server
- PostgreSQL for data persistence
- Keycloak for authentication and authorization
- Google social login through Keycloak

## Authentication

Authentication is handled by Keycloak using OpenID Connect.

The Angular client uses the Authorization Code Flow with PKCE. Access tokens obtained from Keycloak are sent to the Spring Boot backend as Bearer tokens.

The Spring Boot backend operates solely as a resource server and validates the access tokens issued by Keycloak.

## Tech Stack

- Angular
- Spring Boot
- Spring Security
- PostgreSQL
- Keycloak
- OpenID Connect
- OAuth 2.0
- Authorization Code Flow with PKCE
- Google OAuth

## Project Structure

```text
event-planner/
├── frontend/     # Angular application
├── backend/      # Spring Boot resource server
├── keycloak/     # Keycloak configuration
└── DB/           # Database scripts