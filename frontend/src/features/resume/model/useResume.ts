'use client';

import { useState } from 'react';
import {
  getResumeApi,
  createResumeApi,
  updateResumeApi,
  saveResumeApi,
  deleteResumeApi,
} from '@/entities/resume/api/resumeApi';
import { Resume, ResumeCreateRequest } from '@/entities/resume/model';

export function useResume() {
  const [error, setError] = useState<Error | null>(null);

  // 이력서 조회
  const getResume = async (resumeId: number): Promise<Resume> => {
    setError(null);
    try {
      const resume = await getResumeApi(resumeId);
      return resume;
    } catch (e) {
      const err = e instanceof Error ? e : new Error('이력서 조회 실패');
      setError(err);
      throw err;
    }
  };

  // 이력서 생성
  const createResume = async (data: ResumeCreateRequest): Promise<number> => {
    setError(null);
    try {
      const resumeId = await createResumeApi(data);
      return resumeId;
    } catch (e) {
      const err = e instanceof Error ? e : new Error('이력서 생성 실패');
      setError(err);
      throw err;
    }
  };

  // 이력서 수정
  const updateResume = async (
    resumeId: number,
    data: ResumeCreateRequest
  ): Promise<number> => {
    setError(null);
    try {
      const updatedResumeId = await updateResumeApi(resumeId, data);
      return updatedResumeId;
    } catch (e) {
      const err = e instanceof Error ? e : new Error('이력서 수정 실패');
      setError(err);
      throw err;
    }
  };

  // 이력서 저장
  const saveResume = async (
    resumeId: number,
    data: Partial<ResumeCreateRequest>
  ): Promise<number> => {
    setError(null);
    try {
      const savedResumeId = await saveResumeApi(resumeId, data);
      return savedResumeId;
    } catch (e) {
      const err = e instanceof Error ? e : new Error('저장 실패');
      setError(err);
      throw err;
    }
  };

  // 이력서 삭제
  const deleteResume = async (resumeId: number): Promise<void> => {
    setError(null);
    try {
      await deleteResumeApi(resumeId);
    } catch (e) {
      const err = e instanceof Error ? e : new Error('이력서 삭제 실패');
      setError(err);
      throw err;
    }
  };

  return {
    getResume,
    createResume,
    updateResume,
    saveResume,
    deleteResume,
    error,
  };
}

