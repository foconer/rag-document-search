import { useState } from "react";
import { uploadDocument } from "../services/documentService";

function DocumentUpload() {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const [message, setMessage] = useState("");

    const handleUpload = async () => {

        if (!selectedFile) {
            setMessage("Please select a PDF.");
            return;
        }
    
        try {
            const result = await uploadDocument(selectedFile);
    
            setMessage(
                `${result.fileName} uploaded. Pages: ${result.pages}, Characters: ${result.characters}`
            );
    
        } catch (error) {
            console.error(error);
            setMessage("Upload failed.");
        }
    };

    return (
        <div>
            <h2>Upload PDF</h2>

            <input
                type="file"
                accept=".pdf"
                onChange={(e) =>
                    setSelectedFile(e.target.files?.[0] ?? null)
                }
            />

            <button onClick={handleUpload}>
                Upload
            </button>

            <p>{message}</p>
        </div>
    );
}

export default DocumentUpload;