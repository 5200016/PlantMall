import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysBanner } from 'app/shared/model/sys-banner.model';
import { SysBannerService } from './sys-banner.service';
import { SysBannerComponent } from './sys-banner.component';
import { SysBannerDetailComponent } from './sys-banner-detail.component';
import { SysBannerUpdateComponent } from './sys-banner-update.component';
import { SysBannerDeletePopupComponent } from './sys-banner-delete-dialog.component';
import { ISysBanner } from 'app/shared/model/sys-banner.model';

@Injectable({ providedIn: 'root' })
export class SysBannerResolve implements Resolve<ISysBanner> {
    constructor(private service: SysBannerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysBanner> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysBanner>) => response.ok),
                map((sysBanner: HttpResponse<SysBanner>) => sysBanner.body)
            );
        }
        return of(new SysBanner());
    }
}

export const sysBannerRoute: Routes = [
    {
        path: 'sys-banner',
        component: SysBannerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysBanner.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-banner/:id/view',
        component: SysBannerDetailComponent,
        resolve: {
            sysBanner: SysBannerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysBanner.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-banner/new',
        component: SysBannerUpdateComponent,
        resolve: {
            sysBanner: SysBannerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysBanner.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-banner/:id/edit',
        component: SysBannerUpdateComponent,
        resolve: {
            sysBanner: SysBannerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysBanner.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysBannerPopupRoute: Routes = [
    {
        path: 'sys-banner/:id/delete',
        component: SysBannerDeletePopupComponent,
        resolve: {
            sysBanner: SysBannerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysBanner.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
