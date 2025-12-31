import { get } from '@/shared/lib/fetcher';
import { CodeListResponse } from '../model';

// 전체 코드 리스트 조회
export async function getCodeListApi(): Promise<CodeListResponse[]> {
  const res = await get('/api/code/');
  if (!res.ok) {
    throw new Error('코드 리스트 조회 실패');
  }
  return res.json();
}

// 그룹 코드로 코드 리스트 조회
export async function getCodeListByGroupCodeApi(
  groupCode: string
): Promise<CodeListResponse[]> {
  const res = await get(`/api/code/${groupCode}`);
  if (!res.ok) {
    throw new Error('코드 리스트 조회 실패');
  }
  return res.json();
}

