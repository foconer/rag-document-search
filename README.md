Simple Architecture
```
                 React UI
                    |
                    |
             Spring Boot API
                    |
        +-----------+------------+
        |                        |
 PDF Upload                 User Question
        |                        |
        v                        v
 PDF Parser              Create Query Embedding
        |                        |
        v                        |
 Text Chunking                  |
        |                        |
        v                        |
 Local Embedding Model <---------+
        |
        v
 PostgreSQL + pgvector
        |
        |
 Vector Similarity Search
        |
        v
 Return Relevant Text Chunks
```
 
