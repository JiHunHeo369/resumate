export async function loginApi(loginId: string, password: string) {
  const res = await fetch('/auth/login', {
    method: 'POST',
    body: JSON.stringify({ loginId, password }),
    headers: { 'Content-Type': 'application/json' },
  });

  if (!res.ok) throw new Error('Login failed');
  return res.json();
}