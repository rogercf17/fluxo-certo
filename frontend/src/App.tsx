import { useQuery } from '@tanstack/react-query'

export default function App() {
  const { data, isPending, isError } = useQuery({
    queryKey: ['health'],
    queryFn: async () => {
      const res = await fetch('/actuator/health')
      if (!res.ok) throw new Error(`HTTP ${res.status}`)
      return res.json() as Promise<{ status: string }>
    },
  })

  return (
    <main>
      <h1 className="font-bold text-lg">FluxoCerto</h1>
      {isPending && <p>Verificando backend...</p>}
      {isError && <p>Backend indisponível.</p>}
      {data && <p>Backend: {data.status}</p>}
    </main>
  )
}
