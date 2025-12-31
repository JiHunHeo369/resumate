// 코드 리스트 엔티티 타입 정의

export interface CodeListResponse {
  id: number;
  groupCode: string;
  code: string;
  name: string;
  createdBy: number;
  createdAt: string; // LocalDateTime -> ISO 8601 string
  updatedBy?: number | null;
  updatedAt?: string | null; // LocalDateTime -> ISO 8601 string
}

