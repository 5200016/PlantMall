/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponClassifyComponent } from 'app/entities/sys-coupon-classify/sys-coupon-classify.component';
import { SysCouponClassifyService } from 'app/entities/sys-coupon-classify/sys-coupon-classify.service';
import { SysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';

describe('Component Tests', () => {
    describe('SysCouponClassify Management Component', () => {
        let comp: SysCouponClassifyComponent;
        let fixture: ComponentFixture<SysCouponClassifyComponent>;
        let service: SysCouponClassifyService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponClassifyComponent],
                providers: []
            })
                .overrideTemplate(SysCouponClassifyComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponClassifyComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponClassifyService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysCouponClassify(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysCouponClassifies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
