export interface User {
  id: number;
  roleId: number;
  roleCode: string;
  loginId: string;
  name: string;
  image?: string | null;
  password: string;
  createdBy: number;
  createdAt: string; // 또는 Date
  updatedBy?: number | null;
  updatedAt?: string | null; // 또는 Date
  token?: string;
}
