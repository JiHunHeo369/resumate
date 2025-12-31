export interface ResumeListResponse {
  resumeId: number;
  userId: number;
  userName: string;
  image: string;
  title: string;
  introduction: string;
  createdBy: number;
  createdAt: string;
  jobCode: string;
  jobName: string;
}

export interface CardProps {
  data: ResumeListResponse;
}