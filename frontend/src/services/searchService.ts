const API_URL = "http://localhost:8080";

export interface SearchResult {
  content: string;
  score: number | null;
}

export async function searchDocuments(
  query: string
): Promise<SearchResult[]> {

  const response = await fetch(
    `${API_URL}/api/search?query=${encodeURIComponent(query)}`
  );

  if (!response.ok) {
    throw new Error("Search failed");
  }

  return response.json();
}