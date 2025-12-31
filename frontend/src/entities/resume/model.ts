// 이력서 엔티티 타입 정의 (백엔드 모델에 맞춤)

export interface ResumeEducationRequest {
  schoolName: string;
  entranceAt: string; // LocalDateTime -> ISO 8601 string
  graduateAt: string; // LocalDateTime -> ISO 8601 string
  major: string;
}

export interface ResumeCareerRequest {
  companyName: string;
  job: string;
  startedAt: string; // LocalDateTime -> ISO 8601 string
  endedAt: string; // LocalDateTime -> ISO 8601 string
}

export interface ResumeCreateRequest {
  image?: string | null;
  title: string; // @NotBlank
  introduction: string; // @NotBlank
  careers?: ResumeCareerRequest[];
  educations?: ResumeEducationRequest[];
  certificates?: string[];
  jobs?: string[]; // 희망직무
  skills?: string[];
}

// 응답 모델 (필요시 확장)
export interface Resume extends ResumeCreateRequest {
  id?: number;
  userId?: number;
  createdAt?: string;
  updatedAt?: string;
}

