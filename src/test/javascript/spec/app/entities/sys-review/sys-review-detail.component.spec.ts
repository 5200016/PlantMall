/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysReviewDetailComponent } from 'app/entities/sys-review/sys-review-detail.component';
import { SysReview } from 'app/shared/model/sys-review.model';

describe('Component Tests', () => {
    describe('SysReview Management Detail Component', () => {
        let comp: SysReviewDetailComponent;
        let fixture: ComponentFixture<SysReviewDetailComponent>;
        const route = ({ data: of({ sysReview: new SysReview(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReviewDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysReviewDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysReviewDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysReview).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
