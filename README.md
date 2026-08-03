# RAG Document Search

A Retrieval-Augmented Generation (RAG) application that allows users to upload documents, generate embeddings, and perform semantic search using vector similarity.

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
```
## Tech Stack
- Java / Spring Boot
- Apache PDFBox (extracting text)
- jtokkit (tokenization)
- React + Typescript
- PostgreSQL + pgvector
- Ollama + nomic-embed-text
- Docker Compose
- Flyway

## Features
- Upload documents
- Extract and chunk text
- Generate embeddings using Ollama
- Store embeddings in PostgreSQL vector database
- Perform semantic similarity search

## Local Setup

### Start Services
```bash
docker compose up
```
### Services:
- Frontend: [http://localhost:5173](http://localhost:5173)
- Backend: [http://localhost:8080](http://localhost:8080)
- PostgreSQL: localhost:5432
- Ollama: localhost:11434
### Verify Ollama models
```
% docker exec -it rag-ollama ollama list

NAME                       ID              SIZE      MODIFIED      
nomic-embed-text:latest    0a109f422b47    274 MB    5 minutes ago    
```
### Verify embeddings
```
% docker exec -it rag-postgres psql -U raguser -d ragdb
psql (17.10 (Debian 17.10-1.pgdg12+1))
Type "help" for help.
 
ragdb=# \dt
ragdb=# \d document_chunks
ragdb=# SELECT vector_dims(embedding)
```
## Key Concepts Demonstrated
This project demonstrates:
- Retrieval-Augmented Generation (RAG)
- Semantic search
- Vector embeddings
- AI-assisted document processing
- Spring Boot backend architecture
- React frontend development
- Containerized application development