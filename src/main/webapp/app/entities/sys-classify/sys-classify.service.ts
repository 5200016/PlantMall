import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysClassify } from 'app/shared/model/sys-classify.model';

type EntityResponseType = HttpResponse<ISysClassify>;
type EntityArrayResponseType = HttpResponse<ISysClassify[]>;

@Injectable({ providedIn: 'root' })
export class SysClassifyService {
    public resourceUrl = SERVER_API_URL + 'api/sys-classifies';

    constructor(protected http: HttpClient) {}

    create(sysClassify: ISysClassify): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysClassify);
        return this.http
            .post<ISysClassify>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysClassify: ISysClassify): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysClassify);
        return this.http
            .put<ISysClassify>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysClassify>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysClassify[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysClassify: ISysClassify): ISysClassify {
        const copy: ISysClassify = Object.assign({}, sysClassify, {
            createTime: sysClassify.createTime != null && sysClassify.createTime.isValid() ? sysClassify.createTime.toJSON() : null,
            updateTime: sysClassify.updateTime != null && sysClassify.updateTime.isValid() ? sysClassify.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysClassify: ISysClassify) => {
                sysClassify.createTime = sysClassify.createTime != null ? moment(sysClassify.createTime) : null;
                sysClassify.updateTime = sysClassify.updateTime != null ? moment(sysClassify.updateTime) : null;
            });
        }
        return res;
    }
}
