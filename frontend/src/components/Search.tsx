import { useState } from "react";
import {
  searchDocuments,
  type SearchResult
} from "../services/searchService";


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
    <div>

      <h2>Document Search</h2>

      <input
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Ask a question..."
      />

      <button onClick={handleSearch}>
        Search
      </button>


      {loading && (
        <p>Searching...</p>
      )}


      {results.map((result, index) => (
        <div key={index}>
          <hr />
          <p>
            {result.content}
          </p>

          {result.score && (
            <small>
              Score: {result.score}
            </small>
          )}

        </div>
      ))}

    </div>
  );
}