import { get } from '@/shared/lib/fetcher';

export async function listApi() {
  return Promise.resolve([
    {
      resumeId: 1,
      userId: 1,
      userName: '김고슴',
      image: '/images/고슴도치.png',
      title: '김고슴 이력서',
      introduction: '어제보다 오늘 더 성장하는 프론트엔드 개발자 김고슴입니다.',
      createdBy: 1,
      createdAt: '2024-01-01T00:00:00',
      jobCode: 'FE',
      jobName: '개발자',
    },
    {
      resumeId: 2,
      userId: 2,
      userName: '박고양',
      image: '/images/고양이.png',
      title: '박고양 이력서',
      introduction: '사용자 경험을 최우선으로 생각하는 디자이너입니다.',
      createdBy: 2,
      createdAt: '2024-01-02T00:00:00',
      jobCode: 'DE',
      jobName: '디자이너',
    },
    {
      resumeId: 3,
      userId: 3,
      userName: '이여우',
      image: '/images/여우.png',
      title: '이여우  이력서',
      introduction: '안정적이고 확장 가능한 서버를 구축하는 개발자입니다.',
      createdBy: 3,
      createdAt: '2024-01-03T00:00:00',
      jobCode: 'BE',
      jobName: '개발자',
    },
    {
      resumeId: 4,
      userId: 4,
      userName: '정토끼',
      image: '/images/토끼.png',
      title: '정토끼 이력서',
      introduction: '만능 풀스택 개발자입니다.',
      createdBy: 4,
      createdAt: '2024-01-04T00:00:00',
      jobCode: 'FS',
      jobName: '개발자',
    },
  ]);

  // const res = await get('/api/resume');
  // return res.json();
}
