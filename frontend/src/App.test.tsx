import { render, screen } from "@testing-library/react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { expect, test, vi } from "vitest";
import App from "./App"

test("Mostra o status do backend", async () => {
    vi.stubGlobal(
        "fetch",
        vi.fn().mockResolvedValue({ ok: true, json: async () => ({ status: "UP" }) })
    )

    render(
        <QueryClientProvider client={new QueryClient()}>
            <App />
        </QueryClientProvider>
    )

    expect(await screen.findByText("Backend: UP")).toBeInTheDocument()
})