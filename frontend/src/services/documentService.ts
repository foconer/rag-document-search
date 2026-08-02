import type { UploadResponse } from "../types/UploadResponse";

const API_URL = "http://localhost:8080";

export async function uploadDocument(file: File): Promise<UploadResponse> {
    const formData = new FormData();
    formData.append("file", file);

    const response = await fetch(`${API_URL}/api/documents/upload`, {
        method: "POST",
        body: formData,
    });

    if (!response.ok) {
        throw new Error("Upload failed");
    }

    return response.json();
}