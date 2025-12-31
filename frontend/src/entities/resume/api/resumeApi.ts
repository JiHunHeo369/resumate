import { get, post, put, del } from '@/shared/lib/fetcher';
import { Resume, ResumeCreateRequest } from '../model';

// 이력서 조회
export async function getResumeApi(resumeId: number): Promise<Resume> {
  const res = await get(`/api/resume/${resumeId}`);
  if (!res.ok) {
    throw new Error('이력서 조회 실패');
  }
  return res.json();
}

// 이력서 생성
export async function createResumeApi(
  data: ResumeCreateRequest
): Promise<number> {
  const res = await post('/api/resume/', data);
  if (!res.ok) {
    throw new Error('이력서 생성 실패');
  }
  return res.json();
}

// 이력서 수정
export async function updateResumeApi(
  resumeId: number,
  data: ResumeCreateRequest
): Promise<number> {
  const res = await put(`/api/resume/${resumeId}`, data);
  if (!res.ok) {
    throw new Error('이력서 수정 실패');
  }
  return res.json();
}

// 이력서 저장
export async function saveResumeApi(
  resumeId: number,
  data: Partial<ResumeCreateRequest>
): Promise<number> {
  const res = await put(`/api/resume/${resumeId}`, data);
  if (!res.ok) {
    throw new Error('저장 실패');
  }
  return res.json();
}

// 이력서 삭제
export async function deleteResumeApi(resumeId: number): Promise<void> {
  const res = await del(`/api/resume/${resumeId}`);
  if (!res.ok) {
    throw new Error('이력서 삭제 실패');
  }
}

