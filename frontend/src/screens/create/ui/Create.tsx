'use client';

import { useEffect, useState } from 'react';
import { useResume } from '@/features/resume/model/useResume';
import { Resume, ResumeEducationRequest, ResumeCareerRequest } from '@/entities/resume/model';
import { getCodeListByGroupCodeApi } from '@/entities/code/api/codeApi';
import { CodeListResponse } from '@/entities/code/model';
import Button from '@/shared/ui/Button';

type CreateProps = {
  resumeId?: number;
};

export default function Create({ resumeId: initialResumeId }: CreateProps = {}) {
  const { getResume, updateResume, createResume } = useResume();
  const [resume, setResume] = useState<Resume | null>(null);
  const [resumeId, setResumeId] = useState<number | undefined>(initialResumeId);

  // 폼 데이터 상태
  const [image, setImage] = useState<string | null>(null);
  const [imagePreview, setImagePreview] = useState<string | null>(null);
  const [shortIntroduction, setShortIntroduction] = useState<string>('');
  const [jobs, setJobs] = useState<string[]>([]);
  const [jobInput, setJobInput] = useState<string>('');
  const [skills, setSkills] = useState<string[]>([]);
  const [skillInput, setSkillInput] = useState<string>('');
  const [educations, setEducations] = useState<ResumeEducationRequest[]>([]);
  const [careers, setCareers] = useState<ResumeCareerRequest[]>([]);
  const [certificates, setCertificates] = useState<string[]>([]);
  const [certificateInput, setCertificateInput] = useState<string>('');
  const [introduction, setIntroduction] = useState<string>('');
  const [title, setTitle] = useState<string>('');

  // 코드 리스트 상태 (희망직무만 사용)
  const [jobCodes, setJobCodes] = useState<CodeListResponse[]>([]);

  // 코드 리스트 로드 (희망직무만)
  useEffect(() => {
    const loadCodes = async () => {
      try {
        const jobs = await getCodeListByGroupCodeApi('jobs');
        setJobCodes(jobs);
      } catch (error) {
        console.error('코드 리스트 조회 실패:', error);
      }
    };
    loadCodes();
  }, []);

  // 이력서 데이터 로드 (resumeId가 있을 때만)
  useEffect(() => {
    const loadResume = async () => {
      if (!resumeId) {
        return;
      }
      try {
        const data = await getResume(resumeId);
        setResume(data);
        setJobs(data.jobs || []);
        setSkills(data.skills || []);
        setEducations(data.educations || []);
        setCareers(data.careers || []);
        setCertificates(data.certificates || []);
        setIntroduction(data.introduction || '');
        setTitle(data.title || '');
        setImage(data.image || null);
        setImagePreview(data.image || null);
        // 간략한 자기소개는 introduction의 일부로 사용하거나 별도 관리
        setShortIntroduction(data.introduction?.split('\n')[0] || '');
      } catch (error) {
        console.error('이력서 조회 실패:', error);
      }
    };
    loadResume();
  }, [resumeId, getResume]);

  // 이미지 업로드 핸들러
  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        const result = reader.result as string;
        setImagePreview(result);
        setImage(result);
      };
      reader.readAsDataURL(file);
    }
  };

  // 저장 핸들러
  const handleSave = async () => {
    try {
      const data = {
        image,
        title,
        introduction,
        jobs,
        skills,
        educations,
        careers,
        certificates,
      };

      if (resumeId) {
        await updateResume(resumeId, {
          ...data,
          title: title || '제목 없음',
          introduction: introduction || '',
        });
      } else {
        const createdResumeId = await createResume({
          ...data,
          title: title || '제목 없음',
          introduction: introduction || '',
        });
        setResumeId(createdResumeId);
      }
      alert('저장되었습니다.');
    } catch (error) {
      console.error('저장 실패:', error);
      alert('저장에 실패했습니다.');
    }
  };


  // 희망직무 추가
  const handleAddJob = (jobValue?: string) => {
    const value = (jobValue || jobInput).trim();
    if (value && !jobs.includes(value)) {
      setJobs([...jobs, value]);
      setJobInput('');
    }
  };

  // 희망직무 삭제
  const handleRemoveJob = (index: number) => {
    setJobs(jobs.filter((_, i) => i !== index));
  };

  // 스킬 추가
  const handleAddSkill = () => {
    if (skillInput.trim()) {
      setSkills([...skills, skillInput.trim()]);
      setSkillInput('');
    }
  };

  // 스킬 삭제
  const handleRemoveSkill = (index: number) => {
    setSkills(skills.filter((_, i) => i !== index));
  };

  // 자격증 추가
  const handleAddCertificate = () => {
    if (certificateInput.trim()) {
      setCertificates([...certificates, certificateInput.trim()]);
      setCertificateInput('');
    }
  };

  // 자격증 삭제
  const handleRemoveCertificate = (index: number) => {
    setCertificates(certificates.filter((_, i) => i !== index));
  };

  // 학력 추가
  const handleAddEducation = () => {
    setEducations([
      ...educations,
      {
        schoolName: '',
        entranceAt: '',
        graduateAt: '',
        major: '',
      },
    ]);
  };

  // 학력 수정
  const handleUpdateEducation = (
    index: number,
    field: keyof ResumeEducationRequest,
    value: string
  ) => {
    const updated = [...educations];
    updated[index] = { ...updated[index], [field]: value };
    setEducations(updated);
  };

  // 학력 삭제
  const handleRemoveEducation = (index: number) => {
    setEducations(educations.filter((_, i) => i !== index));
  };

  // 경력 추가
  const handleAddCareer = () => {
    setCareers([
      ...careers,
      {
        companyName: '',
        job: '',
        startedAt: '',
        endedAt: '',
      },
    ]);
  };

  // 경력 수정
  const handleUpdateCareer = (
    index: number,
    field: keyof ResumeCareerRequest,
    value: string
  ) => {
    const updated = [...careers];
    updated[index] = { ...updated[index], [field]: value };
    setCareers(updated);
  };

  // 경력 삭제
  const handleRemoveCareer = (index: number) => {
    setCareers(careers.filter((_, i) => i !== index));
  };

  // 날짜를 YYYY-MM-DD 형식으로 변환
  const formatDateForInput = (dateString: string): string => {
    if (!dateString) {
      return '';
    }
    return dateString.split('T')[0];
  };

  // 날짜를 ISO 형식으로 변환
  const formatDateForApi = (dateString: string): string => {
    if (!dateString) {
      return '';
    }
    return new Date(dateString).toISOString();
  };

  // 편집 모드 여부 (resumeId가 없으면 편집 모드)
  const isEditMode = !resumeId;

  return (
    <div className='flex flex-col gap-8 pb-8'>
      <div className='flex justify-around items-center w-full mt-8'>
        <div className='relative w-[300px] h-[300px]'>
          <div className='w-full h-full rounded-lg bg-gray-100 overflow-hidden flex items-center justify-center'>
            {imagePreview ? (
              <img
                src={imagePreview}
                alt='프로필 이미지'
                className='w-full h-full object-cover'
              />
            ) : (
              <div className='text-gray-400 text-sm'>이미지 없음</div>
            )}
          </div>
          {isEditMode && (
            <label className='absolute bottom-0 right-0 bg-[#3f5e25] text-white px-4 py-2 rounded cursor-pointer hover:bg-[#2d4519]'>
              이미지 등록
              <input
                type='file'
                accept='image/*'
                onChange={handleImageUpload}
                className='hidden'
                disabled={!isEditMode}
              />
            </label>
          )}
        </div>

        <div className='flex flex-col gap-3 ml-8 w-full max-w-md'>
          <textarea
            value={shortIntroduction}
            onChange={(e) => {
              setShortIntroduction(e.target.value);
            }}
            placeholder='간략한 자기소개를 입력하세요'
            className='w-full border border-gray-300 px-3 py-2 rounded min-h-[100px] resize-none'
            disabled={!isEditMode}
            readOnly={!isEditMode}
          />
        </div>
      </div>

      {/* 희망직무 */}
      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>희망직무</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <div className='flex flex-wrap gap-2 mb-3'>
          {jobs.map((job, index) => (
            <div
              key={index}
              className={`px-4 py-1 rounded-full bg-gray-100 text-gray-700 text-sm border border-gray-300 ${isEditMode ? 'cursor-pointer' : ''
                }`}
            >
              {job}
              {isEditMode && (
                <span
                  onClick={() => {
                    handleRemoveJob(index);
                  }}
                  className='ml-2'
                >
                  ×
                </span>
              )}
            </div>
          ))}
        </div>

        {isEditMode && (
          <div className='relative'>
            <select
              value={jobInput}
              onChange={(e) => {
                const selectedValue = e.target.value;
                if (selectedValue) {
                  handleAddJob(selectedValue);
                }
              }}
              className='w-full border border-gray-300 px-3 py-2 rounded pr-10 appearance-none'
              disabled={!isEditMode}
            >
              <option value=''>희망직무를 선택하세요</option>
              {jobCodes.map((code) => (
                <option key={code.id} value={code.name}>
                  {code.name}
                </option>
              ))}
            </select>
            <div className='absolute right-3 top-1/2 -translate-y-1/2 pointer-events-none'>
              <svg
                className='w-5 h-5 text-gray-400'
                fill='none'
                stroke='currentColor'
                viewBox='0 0 24 24'
              >
                <path
                  strokeLinecap='round'
                  strokeLinejoin='round'
                  strokeWidth={2}
                  d='M19 9l-7 7-7-7'
                />
              </svg>
            </div>
          </div>
        )}
      </div>

      {/* 스킬 */}
      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>스킬</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <div className='flex flex-wrap gap-2 mb-3'>
          {skills.map((skill, index) => (
            <div
              key={index}
              className={`px-4 py-1 rounded-full bg-gray-100 text-gray-700 text-sm border border-gray-300 ${isEditMode ? 'cursor-pointer' : ''
                }`}
            >
              {skill}
              {isEditMode && (
                <span
                  onClick={() => {
                    handleRemoveSkill(index);
                  }}
                  className='ml-2'
                >
                  ×
                </span>
              )}
            </div>
          ))}
        </div>

        {isEditMode && (
          <div>
            <input
              type='text'
              value={skillInput}
              onChange={(e) => {
                setSkillInput(e.target.value);
              }}
              onKeyPress={(e) => {
                if (e.key === 'Enter') {
                  handleAddSkill();
                }
              }}
              placeholder='스킬을 입력하세요'
              className='w-full border border-gray-300 px-3 py-2 rounded'
              disabled={!isEditMode}
            />
          </div>
        )}
      </div>

      {/* 학력 */}
      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>학력</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        {educations.map((education, index) => (
          <div key={index} className='flex gap-4 mb-4'>
            <div className='relative w-full'>
              <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
                입학일
              </span>
              <input
                type='date'
                value={formatDateForInput(education.entranceAt)}
                onChange={(e) => {
                  handleUpdateEducation(
                    index,
                    'entranceAt',
                    formatDateForApi(e.target.value)
                  );
                }}
                onClick={(e) => {
                  if (isEditMode) {
                    (e.target as HTMLInputElement).showPicker?.();
                  }
                }}
                className='border border-gray-300 px-3 py-2 rounded w-full'
                disabled={!isEditMode}
                readOnly={!isEditMode}
              />
            </div>
            <div className='relative w-full'>
              <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
                졸업일
              </span>
              <input
                type='date'
                value={formatDateForInput(education.graduateAt)}
                onChange={(e) => {
                  handleUpdateEducation(
                    index,
                    'graduateAt',
                    formatDateForApi(e.target.value)
                  );
                }}
                onClick={(e) => {
                  if (isEditMode) {
                    (e.target as HTMLInputElement).showPicker?.();
                  }
                }}
                className='border border-gray-300 px-3 py-2 rounded w-full'
                disabled={!isEditMode}
                readOnly={!isEditMode}
              />
            </div>

            <div className='relative w-full'>
              <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
                학교
              </span>
              <input
                type='text'
                value={education.schoolName}
                onChange={(e) => {
                  handleUpdateEducation(index, 'schoolName', e.target.value);
                }}
                className='border border-gray-300 px-3 py-2 rounded w-full'
                disabled={!isEditMode}
                readOnly={!isEditMode}
              />
            </div>

            <div className='relative w-full'>
              <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
                학과
              </span>
              <input
                type='text'
                value={education.major}
                onChange={(e) => {
                  handleUpdateEducation(index, 'major', e.target.value);
                }}
                className='border border-gray-300 px-3 py-2 rounded w-full'
                disabled={!isEditMode}
                readOnly={!isEditMode}
              />
            </div>

            {isEditMode && (
              <button
                onClick={() => {
                  handleRemoveEducation(index);
                }}
                className='px-4 py-2 bg-red-200 text-red-700 hover:bg-red-300 rounded whitespace-nowrap'
              >
                삭제
              </button>
            )}
          </div>
        ))}

        {isEditMode && (
          <button
            onClick={handleAddEducation}
            className='px-4 py-2 border border-gray-300 rounded text-sm hover:bg-gray-50'
          >
            + 학력 추가
          </button>
        )}
      </div>

      {/* 경력 */}
      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>경력</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <div className='border border-gray-300 rounded-lg p-4'>
          {careers.map((career, index) => (
            <div key={index} className='flex gap-4 mb-4'>
              <div className='flex gap-2 w-full'>
                <input
                  type='date'
                  value={formatDateForInput(career.startedAt)}
                  onChange={(e) => {
                    handleUpdateCareer(
                      index,
                      'startedAt',
                      formatDateForApi(e.target.value)
                    );
                  }}
                  onClick={(e) => {
                    if (isEditMode) {
                      (e.target as HTMLInputElement).showPicker?.();
                    }
                  }}
                  className='border border-gray-300 px-3 py-2 rounded flex-1'
                  disabled={!isEditMode}
                  readOnly={!isEditMode}
                />
                <span className='self-center'>~</span>
                <input
                  type='date'
                  value={formatDateForInput(career.endedAt)}
                  onChange={(e) => {
                    handleUpdateCareer(
                      index,
                      'endedAt',
                      formatDateForApi(e.target.value)
                    );
                  }}
                  onClick={(e) => {
                    if (isEditMode) {
                      (e.target as HTMLInputElement).showPicker?.();
                    }
                  }}
                  className='border border-gray-300 px-3 py-2 rounded flex-1'
                  disabled={!isEditMode}
                  readOnly={!isEditMode}
                />
              </div>
              <div className='relative w-full'>
                <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
                  회사명
                </span>
                <input
                  type='text'
                  value={career.companyName}
                  onChange={(e) => {
                    handleUpdateCareer(index, 'companyName', e.target.value);
                  }}
                  className='border border-gray-300 px-3 py-2 rounded w-full'
                  disabled={!isEditMode}
                  readOnly={!isEditMode}
                />
              </div>
              <div className='relative w-full'>
                <span className='absolute left-2 -top-1.5 bg-white px-1 text-sm z-10 text-gray-500'>
                  직무
                </span>
                <input
                  type='text'
                  value={career.job}
                  onChange={(e) => {
                    handleUpdateCareer(index, 'job', e.target.value);
                  }}
                  className='border border-gray-300 px-3 py-2 rounded w-full'
                  disabled={!isEditMode}
                  readOnly={!isEditMode}
                />
              </div>
              {isEditMode && (
                <button
                  onClick={() => {
                    handleRemoveCareer(index);
                  }}
                  className='px-4 py-2 bg-red-200 text-red-700 hover:bg-red-300 rounded whitespace-nowrap'
                >
                  삭제
                </button>
              )}
            </div>
          ))}

          {isEditMode && (
            <button
              onClick={handleAddCareer}
              className='w-full mt-2 px-4 py-2 border border-gray-300 rounded text-sm hover:bg-gray-50'
            >
              + 경력 추가
            </button>
          )}
        </div>
      </div>

      {/* 자격증 */}
      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>자격증</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <div className='flex flex-wrap gap-2 mb-3'>
          {certificates.map((cert, index) => (
            <div
              key={index}
              className={`px-4 py-1 rounded-full bg-gray-100 text-gray-700 text-sm border border-gray-300 ${isEditMode ? 'cursor-pointer' : ''
                }`}
            >
              {cert}
              {isEditMode && (
                <span
                  onClick={() => {
                    handleRemoveCertificate(index);
                  }}
                  className='ml-2'
                >
                  ×
                </span>
              )}
            </div>
          ))}
        </div>

        {isEditMode && (
          <div>
            <input
              type='text'
              value={certificateInput}
              onChange={(e) => {
                setCertificateInput(e.target.value);
              }}
              onKeyPress={(e) => {
                if (e.key === 'Enter') {
                  handleAddCertificate();
                }
              }}
              placeholder='자격증을 입력하세요'
              className='w-full border border-gray-300 px-3 py-2 rounded'
              disabled={!isEditMode}
            />
          </div>
        )}
      </div>

      {/* 자기소개 */}
      <div>
        <h2 className='text-lg font-bold mb-2 text-[#3f5e25]'>자기소개</h2>
        <hr className='mb-4 border-[#3f5e25]' />

        <textarea
          value={introduction}
          onChange={(e) => {
            setIntroduction(e.target.value);
          }}
          className='w-full p-4 min-h-[200px] resize-none outline-none border border-gray-300 rounded'
          disabled={!isEditMode}
          readOnly={!isEditMode}
        />
      </div>

      {/* 하단 버튼 */}
      {isEditMode && (
        <div className='flex justify-end gap-3 mt-4'>
          <Button onClick={handleSave}>저장</Button>
        </div>
      )}
    </div>
  );
}
