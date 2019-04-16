import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';

type EntityResponseType = HttpResponse<ISysMemberLevel>;
type EntityArrayResponseType = HttpResponse<ISysMemberLevel[]>;

@Injectable({ providedIn: 'root' })
export class SysMemberLevelService {
    public resourceUrl = SERVER_API_URL + 'api/sys-member-levels';

    constructor(protected http: HttpClient) {}

    create(sysMemberLevel: ISysMemberLevel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMemberLevel);
        return this.http
            .post<ISysMemberLevel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysMemberLevel: ISysMemberLevel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysMemberLevel);
        return this.http
            .put<ISysMemberLevel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysMemberLevel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysMemberLevel[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysMemberLevel: ISysMemberLevel): ISysMemberLevel {
        const copy: ISysMemberLevel = Object.assign({}, sysMemberLevel, {
            createTime:
                sysMemberLevel.createTime != null && sysMemberLevel.createTime.isValid() ? sysMemberLevel.createTime.toJSON() : null,
            updateTime: sysMemberLevel.updateTime != null && sysMemberLevel.updateTime.isValid() ? sysMemberLevel.updateTime.toJSON() : null
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
            res.body.forEach((sysMemberLevel: ISysMemberLevel) => {
                sysMemberLevel.createTime = sysMemberLevel.createTime != null ? moment(sysMemberLevel.createTime) : null;
                sysMemberLevel.updateTime = sysMemberLevel.updateTime != null ? moment(sysMemberLevel.updateTime) : null;
            });
        }
        return res;
    }
}
