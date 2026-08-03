CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE document_chunks (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    chunk_number INTEGER NOT NULL,
    token_count INTEGER NOT NULL
);