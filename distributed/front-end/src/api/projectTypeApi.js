import axiose from '@/utils/axiose'

let base = '/projectType';

export const getProjectTypeOne = params => { return axiose.get(`${base}/findOne`, { params: params }).then(res => res.data); };

export const getProjectTypeListPage = params => { return axiose.get(`${base}/findList`, { params: params }).then(res => res.data); };

export const removeProjectType = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const batchRemoveProjectType = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const editProjectType = params => { return axiose.post(`${base}/update`, params).then(res => res.data); };

export const addProjectType = params => { return axiose.post(`${base}/insert`, params).then(res => res.data); };

export const getProjectTypeNameList = params => { return axiose.get(`${base}/findNameList`, { params: params }).then(res => res.data); };
