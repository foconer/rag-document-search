import { useState } from "react";
import { searchDocuments } from "../services/searchService";
import type { SearchResult } from "../services/searchService";
import "./Search.css";

export default function Search() {

  const [query, setQuery] = useState("");
  const [results, setResults] = useState<SearchResult[]>([]);
  const [loading, setLoading] = useState(false);


  async function handleSearch() {

    if (!query.trim()) {
      return;
    }

    setLoading(true);

    try {
      const data = await searchDocuments(query);
      setResults(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  }


  return (
    <div className="search-container">

      <div className="search-card">

        <h1>
          📄 Document Assistant
        </h1>

        <p className="subtitle">
          Ask questions about your uploaded documents
        </p>


        <div className="search-box">

          <input
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Ask a question..."
            onKeyDown={(e) => {
              if (e.key === "Enter") {
                handleSearch();
              }
            }}
          />

          <button onClick={handleSearch}>
            Search
          </button>

        </div>


        {loading && (
          <div className="loading">
            Searching documents...
          </div>
        )}


        <div className="results">

          {results.map((result, index) => (

            <div className="result-card" key={index}>

              <div className="result-header">
                Result {index + 1}
              </div>


              <p>
                {result.content}
              </p>


              {result.score !== null && (
                <small>
                  Similarity: {result.score}
                </small>
              )}

            </div>

          ))}

        </div>

      </div>

    </div>
  );
}