export interface User {
  id: number;
  role_id: number;
  login_id: string;
  name: string;
  image?: string | null;
  password: string;
  created_by: number;
  created_at: string; // 또는 Date
  updated_by?: number | null;
  updated_at?: string | null; // 또는 Date
}
