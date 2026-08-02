# RAG Document Search Application

## Overview

RAG Document Search is an AI-powered document processing and semantic search application that allows users to upload documents, extract and process their content, and perform intelligent searches based on the meaning of the text rather than simple keyword matching.

The application uses a Retrieval-Augmented Generation (RAG) architecture, where documents are converted into searchable vector representations (embeddings) and stored in a vector database. This enables users to find relevant information from large documents using natural language queries.

The initial use case is employee health benefit document search, where users can upload benefit documents and ask questions such as:

- "What is my deductible?"
- "Does my plan cover dental?"
- "What are my out-of-pocket maximums?"

The system retrieves the most relevant document sections and can provide context-aware answers using AI models.

---



## Architecture Overview

```text
User
 |
 v
React Frontend
 |
 v
Spring Boot Backend
 |
 +--> Document Upload
 |
 +--> PDF Text Extraction (Apache PDFBox)
 |
 +--> Text Cleaning
 |
 +--> Tokenization
 |
 +--> Document Chunking
 |
 +--> Embedding Generation (Ollama + nomic-embed-text)
 |
 v
PostgreSQL + pgvector
 |
 v
Semantic Search
 |
 v
AI Response Generation (future enhancement)
```

---



## Technologies Used



### Frontend

**React + TypeScript**

- Provides the user interface for document uploads and search interactions.
- Communicates with the Spring Boot backend through REST APIs.
- Uses TypeScript for improved type safety and maintainability.

---



### Backend

**Java + Spring Boot**

The backend provides the core application services:

- REST API development
- Document processing workflow
- Business logic orchestration
- Integration with AI and database services

Spring Boot components include:

- Spring Web
- Spring Dependency Injection
- Service-based layered architecture

---



### Document Processing

**Apache PDFBox**

Used for extracting text content from uploaded PDF documents.

Processing flow:

```text
PDF Document
      |
      v
Apache PDFBox
      |
      v
Extracted Text
```

---



### Text Processing

**Custom Text Processing Services**

The application processes extracted text before creating embeddings:

- Removes unnecessary whitespace
- Normalizes document content
- Splits large documents into smaller chunks
- Counts tokens to control chunk size

Chunking is based on token count rather than character length to better align with AI model context limitations.

---



### Tokenization

**jtokkit**

Used for token counting during document chunking.

Purpose:

- Estimate model token usage
- Create appropriately sized document chunks
- Prepare text for embedding generation

---



### Embedding Generation

**Ollama + nomic-embed-text**

The application uses locally running AI models to generate semantic embeddings.

Ollama provides a local AI model runtime, while `nomic-embed-text` converts text into numerical vector representations.

Example:

```text
Document Chunk

"The annual deductible is $1500"

        |
        v

Embedding Model

        |
        v

[0.023, -0.145, 0.876, ...]
```

These vectors capture the meaning of the text and allow semantic similarity searches.

Benefits:

- Runs locally
- No external API dependency
- No embedding API costs
- Keeps documents within the local environment
- Provider abstraction allows future support for OpenAI, AWS Bedrock, or other embedding providers

---



### Vector Database

**PostgreSQL + pgvector**

PostgreSQL is used as the primary data store with the pgvector extension for vector similarity search.

Stores:

- Document metadata
- Document chunks
- Generated embeddings

Enables searches such as:

```text
User Question
      |
      v
Question Embedding
      |
      v
Vector Similarity Search
      |
      v
Relevant Document Chunks
```

---



### Containerization

**Docker + Docker Compose**

The application is containerized for local development.

Services include:

```text
docker-compose

+----------------+
| React          |
+----------------+

+----------------+
| Spring Boot    |
+----------------+

+----------------+
| PostgreSQL     |
| pgvector       |
+----------------+

+----------------+
| Ollama         |
| nomic-embed    |
+----------------+
```

Docker Compose provides a consistent development environment where all application dependencies can be started together.

---



## Future Enhancements

Planned improvements:

- Add LLM-powered question answering
- Implement full RAG response generation
- Store documents and embeddings permanently
- Add authentication and authorization
- Add document management capabilities
- Deploy to Kubernetes / Amazon EKS
- Automate deployment using Terraform, Helm, and GitHub Actions

---



## Key Concepts Demonstrated

This project demonstrates:

- Retrieval-Augmented Generation (RAG)
- Semantic search
- Vector embeddings
- AI-assisted document processing
- Spring Boot backend architecture
- React frontend development
- Containerized application development
- Cloud-native deployment patterns
- Kubernetes and AWS deployment practices

