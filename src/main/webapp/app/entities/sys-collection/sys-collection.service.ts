import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysCollection } from 'app/shared/model/sys-collection.model';

type EntityResponseType = HttpResponse<ISysCollection>;
type EntityArrayResponseType = HttpResponse<ISysCollection[]>;

@Injectable({ providedIn: 'root' })
export class SysCollectionService {
    public resourceUrl = SERVER_API_URL + 'api/sys-collections';

    constructor(protected http: HttpClient) {}

    create(sysCollection: ISysCollection): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCollection);
        return this.http
            .post<ISysCollection>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysCollection: ISysCollection): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCollection);
        return this.http
            .put<ISysCollection>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysCollection>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysCollection[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysCollection: ISysCollection): ISysCollection {
        const copy: ISysCollection = Object.assign({}, sysCollection, {
            createTime: sysCollection.createTime != null && sysCollection.createTime.isValid() ? sysCollection.createTime.toJSON() : null,
            updateTime: sysCollection.updateTime != null && sysCollection.updateTime.isValid() ? sysCollection.updateTime.toJSON() : null
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
            res.body.forEach((sysCollection: ISysCollection) => {
                sysCollection.createTime = sysCollection.createTime != null ? moment(sysCollection.createTime) : null;
                sysCollection.updateTime = sysCollection.updateTime != null ? moment(sysCollection.updateTime) : null;
            });
        }
        return res;
    }
}
