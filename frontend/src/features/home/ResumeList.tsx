'use client';

import { useState, useEffect } from 'react';

import { listApi } from '@/shared/api/home/listApi';
import { ResumeListResponse } from '@/shared/types/resume';
import Swal from 'sweetalert2';
import Card from '@/screens/home/ui/Card';

export function ResumeList() {
  const [list, setList] = useState<ResumeListResponse[]>([]);

  const getResumeList = async () => {
    try {
      const data = await listApi();
      setList(data);
    } catch {
      Swal.fire(
        '데이터 로드 실패',
        '이력서 목록을 불러올 수 없습니다',
        'error'
      );
    }
  };

  useEffect(() => {
    getResumeList();
  }, []);

  return (
    <>
      {list.map((resume) => (
        <Card key={resume.resumeId} data={resume} />
      ))}
    </>
  );
}
