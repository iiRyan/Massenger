# Messenger

Messenger is a RESTful API service built using Java with JAX-RS to manage messages, comments, and profiles. This project was developed to apply my knowledge of Java EE, JAX-RS, and integration with IBM Cloud services such as Cloudant.

---

## Features

- **Message Management**:
  - Create, update, delete, and fetch messages.
  - Add, update, and delete comments for each message.
  - CRUD for profiles.

- **RESTful Design**:
  - Fully compliant with RESTful standards.
  - Supports content negotiation (JSON responses).

- **Database Integration**:
  - Uses IBM Cloudant as the NoSQL database.
  - Efficient handling of message and comment relationships.

- **Pagination**:
  - Fetch paginated lists of messages to reduce response size for large datasets.

---

## Technology Stack

- **Backend**: Java (JDK 17)
- **Framework**: JAX-RS
- **Database**: IBM Cloudant (NoSQL)
- **Build Tool**: Maven
- **Application Server**: OpenLiberty

---

## Prerequisites

- Java 17 or higher
- Maven
- IBM Cloudant account and credentials

---

## Installation

### Clone the Repository

```bash
git clone git@github.com:iiRyan/Massenger.git
cd Massenger

export CLOUDANT_URL="<your-cloudant-url>"
export APIKEY="<your-cloudant-api-key>"

mvn clean install

mvn liberty:run
