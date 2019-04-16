import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysBanner } from 'app/shared/model/sys-banner.model';

type EntityResponseType = HttpResponse<ISysBanner>;
type EntityArrayResponseType = HttpResponse<ISysBanner[]>;

@Injectable({ providedIn: 'root' })
export class SysBannerService {
    public resourceUrl = SERVER_API_URL + 'api/sys-banners';

    constructor(protected http: HttpClient) {}

    create(sysBanner: ISysBanner): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysBanner);
        return this.http
            .post<ISysBanner>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysBanner: ISysBanner): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysBanner);
        return this.http
            .put<ISysBanner>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysBanner>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysBanner[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysBanner: ISysBanner): ISysBanner {
        const copy: ISysBanner = Object.assign({}, sysBanner, {
            createTime: sysBanner.createTime != null && sysBanner.createTime.isValid() ? sysBanner.createTime.toJSON() : null,
            updateTime: sysBanner.updateTime != null && sysBanner.updateTime.isValid() ? sysBanner.updateTime.toJSON() : null
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
            res.body.forEach((sysBanner: ISysBanner) => {
                sysBanner.createTime = sysBanner.createTime != null ? moment(sysBanner.createTime) : null;
                sysBanner.updateTime = sysBanner.updateTime != null ? moment(sysBanner.updateTime) : null;
            });
        }
        return res;
    }
}
