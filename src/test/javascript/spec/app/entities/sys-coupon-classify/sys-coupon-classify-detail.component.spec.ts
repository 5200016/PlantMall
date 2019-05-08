/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponClassifyDetailComponent } from 'app/entities/sys-coupon-classify/sys-coupon-classify-detail.component';
import { SysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';

describe('Component Tests', () => {
    describe('SysCouponClassify Management Detail Component', () => {
        let comp: SysCouponClassifyDetailComponent;
        let fixture: ComponentFixture<SysCouponClassifyDetailComponent>;
        const route = ({ data: of({ sysCouponClassify: new SysCouponClassify(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponClassifyDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysCouponClassifyDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponClassifyDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysCouponClassify).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
