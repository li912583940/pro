import axiose from '@/utils/axiose'

let base = '/projectFile';

export const getProjectFileOne = params => { return axiose.get(`${base}/findOne`, { params: params }).then(res => res.data); };

export const getProjectFileListPage = params => { return axiose.get(`${base}/findList`, { params: params }).then(res => res.data); };

export const removeProjectFile = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const batchRemoveProjectFile = params => { return axiose.post(`${base}/delete`, params).then(res => res.data); };

export const editProjectFile = params => { return axiose.post(`${base}/update`, params).then(res => res.data); };

export const addProjectFile = params => { return axiose.post(`${base}/insert`, params).then(res => res.data); };

export const getProjectFile = params => { return axiose.get(`${base}/downFile`, { params: params }).then(res => res.data); };

