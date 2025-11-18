import { post } from "@/shared/lib/fetcher";

export async function loginApi(loginId: string, password: string) {
  const res = await post('/api/users/login', { loginId, password });
  return res.json();
}